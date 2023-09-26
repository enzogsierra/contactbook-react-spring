import { useEffect, useState } from "react";
import ContactService from "../services/ContactService";
import { Link } from "react-router-dom";
import Swal from "sweetalert2";


export default function Home()
{
    const [contacts, setContacts] = useState([]); // Almacena la lista de contactos

    useEffect(() =>
    {
        loadContacts();
    }, [contacts]);


    // Función para obtener todos los contactos
    const loadContacts = () =>
    {
        ContactService.findAll().then(response => // Enviar petición a la api para que nos devuelva todos los contactos
        {
            setContacts(response.data);
        });
    };

    // Función llamada cuando se presiona el boton para eliminar un contacto
    const deleteContact = (id, name, lastName) =>
    {
        // Mostrar advertencia antes de eliminar
        Swal.fire(
        {
            title: "Eliminar contacto",
            html: `¿Seguro que quieres eliminar a <span class="fw-semibold">'${name + " " + lastName}'</span>? Esta acción es irreversible!`,
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Eliminar",
            cancelButtonText: "Cancelar"
        }).then((result) => 
        {
            if(result.isConfirmed) // Si el usuario aceptó eliminar el contacto
            {
                // Enviamos una petición a la API para que elimine el contacto
                ContactService.deleteById(id).then(response =>
                {
                    // Mostramos un mensaje de confirmación
                    Swal.fire(
                    {
                        title: "Eliminado!",
                        html: `<span class="fw-semibold">'${name + " " + lastName}'</span> fue eliminado de tu agenda de contactos`,
                        icon: "success"
                    });

                    loadContacts(); // Actualizamos la lista de contactos
                }).catch(() => // Ocurrió un error al eliminar el contacto
                {
                    // Mostramos un mensaje de información
                    Swal.fire(
                    {
                        title: "Ocurrió un problema",
                        html: `Este contacto no pudo ser eliminado, intenta recargando la página.`,
                        icon: "question"
                    });
                });
            }
        });
    };

    return (
        <main className="container">
            <div>
                <h2 className="text-center">Agenda de contactos</h2>
                <Link to="/form" className="my-2 btn btn-primary"><i className="bi bi-person-plus-fill"></i> Nuevo contacto</Link>
            </div>

            <div className="p-4 border rounded shadow">
                <table className="table table-bordered table-striped table-hover caption-top align-middle">
                    <caption>Mostrando {contacts.length} contactos</caption>
                    
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Teléfono</th>
                            <th>Correo electrónico</th>
                            <th></th>
                        </tr>
                    </thead>

                    <tbody>
                    { 
                        // Mapear la listsa de contactos y añadirlos a la tabla
                        contacts.map(contact =>
                        {
                            return (
                                <tr key={contact.id}>
                                    <td>#{contact.id}</td>
                                    <td>{contact.name}</td>
                                    <td>{contact.lastName}</td>
                                    <td>{contact.phoneNumber}</td>
                                    <td>{contact.email}</td>
                                    <td className="d-flex gap-2">
                                        <Link to={`/form/${contact.id}`} className="btn btn-sm btn-warning" title="Editar contacto">
                                            <i className="bi bi-pencil-square"></i>
                                        </Link>
                                        <button className="btn btn-sm btn-danger" title="Eliminar contacto" onClick={() => deleteContact(contact.id, contact.name, contact.lastName)}>
                                            <i className="bi bi-trash-fill"></i>
                                        </button>
                                    </td>
                                </tr> 
                            );
                        })
                    }
                    </tbody>
                </table>
            </div>
        </main>
    );
} 