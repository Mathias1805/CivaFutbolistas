import React, {useEffect, useState} from 'react';

import './App.css';



function App() {


    const [a, setA] = useState(0);
    const sumarA = () => {
        setA(a+1 === dataTable.totalPages ? a : a + 1);
    };
    const restarA = () => {
        setA(a === 0 ? a : a - 1);
    }
    const [token, setToken] = useState(null);
    const [dataTable, setDataTable] = useState({
        content: [],
        pageable: {},
        last: false,
        totalElements: 0,
        totalPages: 0,
        first: false,
        size: 0,
        number: 0,
        sort: {},
        numberOfElements: 0,
        empty: false
    });
    const credenciales = {
        usuario: "root",
        contraseia: "root"
    }

    useEffect(() => {
        const obtenerToken = async () => {
            try {
                const response = await fetch('http://localhost:8080/Auth/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json' // Especificar el tipo de contenido como JSON
                    },
                    body: JSON.stringify(credenciales)
                });
                if (!response.ok) {
                    throw new Error('Error al iniciar sesión');
                }
                const data = await response.json();
                setToken(data.token);
            } catch (error) {
                console.error('Error:', error);
            }
        };
        obtenerToken();
    }, []); // Se ejecuta solo una vez al montar el componente

    useEffect(() => {
        const obtenerDatos = async () => {
            if (token) {
                try {
                    const response = await fetch('http://localhost:8080/futbolista?size=6&&page=0', {
                        method: 'GET',
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${token}`
                        }
                    });
                    if (!response.ok) {
                        throw new Error('Error al obtener datos');
                    }
                    const data = await response.json();
                    setDataTable(data)
                } catch (error) {
                    console.error('Error:', error);
                }
            }
        };
        obtenerDatos();
    }, [token]);

    useEffect(() => {
        const obtenerDatos = async () => {
            if (token) {
                try {
                    const response = await fetch('http://localhost:8080/futbolista?size=6&&page=' + a, {
                        method: 'GET',
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${token}`
                        }
                    });
                    if (!response.ok) {
                        throw new Error('Error al obtener datos');
                    }
                    const data = await response.json();
                    setDataTable(data)
                } catch (error) {
                    console.error('Error:', error);
                }
            }
        };
        obtenerDatos();
    }, [a]);
    const Info = (e: any) => {
        fetch('http://localhost:8080/futbolista/' + e.idFutbolista, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => response.json()) // Convertir la respuesta a JSON
            .then(data => {
                // Imprimir la respuesta en una alerta
                alert("Nombre: "+data.nombre +
                    "\nApellidos: "+ data.apellidos +
                    "\nPosición: "+ data.posicion.posicion +
                    "\nCaracterísticas: "+ data.caracteristicas +
                    "\nFecha de Nacimiento: "+ new Date(data.fechaNacimiento).toLocaleDateString());
            })
            .catch(error => {
                console.error('Error al obtener información:', error);
                // Si ocurre un error, también puedes mostrarlo en una alerta
                alert('Error al obtener información');
            });
    }
    return (
        <div className="App flex-col h-screen">
            <div className='flex w-full h-[70px] bg-gradient-to-r from-indigo-500 via-purple-500 to-pink-500'>
                <h1 className='font-mono text-3xl text-blue-300 pl-5 pt-5'>Civa - Futbolistas</h1>
            </div>
            <div className="bg-amber-300/75 relative overflow-x-auto flex justify-center py-20 h-5/6">
                <table className=" w-11/12 text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                    <thead className="text-xs uppercase dark:bg-gray-700 dark:text-gray-400 ">
                    <tr>
                        <th scope="col" className="px-6 py-3">
                            <div className="flex items-center">
                                Nombre
                            </div>
                        </th>
                        <th scope="col" className="px-6 py-3">
                            <div className="flex items-center">
                                Apellidos
                            </div>
                        </th>
                        <th scope="col" className="px-6 py-3">
                            <div className="flex items-center">
                                Fecha de Nacimiento
                            </div>
                        </th>
                        <th scope="col" className="px-6 py-3">
                            <div className="flex items-center">
                                Posición
                            </div>
                        </th>
                        <th scope="col" className="px-6 py-3">
                            <span className="sr-only">Edit</span>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    {dataTable.content.map((elemento: any, index) => (
                        <tr key={elemento.idFutbolista}
                            className="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                            <th scope="row"
                                className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                {elemento.nombre}
                            </th>
                            <td className="px-6 py-4">
                                {elemento.apellidos}
                            </td>
                            <td className="px-6 py-4">
                                {new Date(elemento.fechaNacimiento).toLocaleDateString()}
                            </td>
                            <td className="px-6 py-4">
                                {elemento.posicion.posicion}
                            </td>
                            <td className="px-6 py-4 text-right">
                                <button onClick={() => Info(elemento)} type="button"
                                   className="font-medium text-blue-600 dark:text-blue-500 hover:underline">Info</button>
                            </td>
                        </tr>
                    ))}

                    </tbody>
                </table>
            </div>

            <div
                className='fixed bottom-0 left-0  flex w-full h-[70px] bg-gradient-to-r from-indigo-500 via-purple-500 to-pink-500 justify-between items-center'>
                <h1 className='font-mono text-blue-300 px-5 py-5'>Mathias Nicolas Principe Carranza</h1>
                <button onClick={restarA} type="button"
                        className="text-white bg-red-700 hover:bg-red-800 focus:outline-none focus:ring-4 focus:ring-red-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900">
                    Anterior
                </button>
                <button onClick={sumarA} type="button"
                        className="text-white bg-green-700 hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800">
                    Siguiente
                </button>
                <p className='font-mono text-blue-300 px-5 py-5'> mnicolasprincipe@gmail.com</p>
            </div>
        </div>
    );
}

export default App;
