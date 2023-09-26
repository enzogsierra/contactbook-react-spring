import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import ContactService from "../services/ContactService";



export default function ContactForm()
{
    const navigate = useNavigate();

    const {id} = useParams(); // Obtenemos el ID del contacto, si está presente ("/form/{id}")

    const [name, setName] = useState("");
    const [lastName, setLastName] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [email, setEmail] = useState("");
    const [errors, setErrors] = useState([]); // Almacena los errores de validación devueltos por la API

    useEffect(() =>
    {
        if(id !== undefined) // ID presente en la url, está editando un contacto
        {
            // Obtener datos del contacto según el ID
            ContactService.findById(id).then(response =>
            {
                const data = response.data;
                setName(data.name || ""); // Establecer el nombre, o un string vacío (para evitar errores de valores null en el input[value])
                setLastName(data.lastName || "");
                setPhoneNumber(data.phoneNumber || "");
                setEmail(data.email || "");
            }).catch(() => { // Ocurrió un error, ID no válida tal vez?
                navigate("/"); // Volver a la página principal
            }); 
        }
    }, [id, navigate]);


    // Función llamada cuando el form hace submit
    const saveContact = e =>
    {
        e.preventDefault();

        const contact = {id, name, lastName, phoneNumber, email}; // Obtener el ID y los valores de los campos
        ContactService.save(contact).then(() => {
            navigate("/"); // Contacto guardado correctamente, volver a la página principal
        })
        .catch(error => { // Ocurrió un error
            if(error.response.data) // Si el error ocurrido fue por errores en la validación de los campos
            {
                const {errors} = error.response.data; // Obtenemos el arreglo de errores ("field": "defaultMessage")
                setErrors(errors); // Establecer los errores de validacion
            }
        });
    }

    return (
        <main className="container">
            <h2>{id === undefined ? 'Nuevo contacto' : 'Editar contacto'}</h2>

            <div className="p-4 bg-light border rounded shadow">
                <p className="mb-3 small text-muted">* Campos requeridos</p>

                <form onSubmit={saveContact} noValidate>
                    <div className="form-group mb-3">
                        <label className="form-label" htmlFor="name">Nombre *</label>
                        <input className={`form-control ${errors.name ? 'is-invalid' : ''}`} type="text" name="name" id="name" placeholder="Name" value={name} onChange={e => setName(e.target.value)} required></input>
                        <div className="text-danger">{errors.name}</div> { /* Mostrar errores de validación para el campo "name", si existe */}
                    </div>

                    <div className="form-group mb-3">
                        <label className="form-label" htmlFor="lastName">Apellido *</label>
                        <input className={`form-control ${errors.lastName ? 'is-invalid' : ''}`} type="text" name="lastName" id="lastName" placeholder="Last name" value={lastName} onChange={e => setLastName(e.target.value)} required></input>
                        <div className="text-danger">{errors.lastName}</div>
                    </div>

                    <div className="form-group mb-3">
                        <label className="form-label" htmlFor="phoneNumber">Teléfono *</label>
                        <input className={`form-control ${errors.phoneNumber ? 'is-invalid' : ''}`} type="tel" name="phoneNumber" id="phoneNumber" placeholder="Phone number" value={phoneNumber} onChange={e => setPhoneNumber(e.target.value)} required></input>
                        <div className="text-danger">{errors.phoneNumber}</div>
                    </div>

                    <div className="form-group mb-3">
                        <label className="form-label" htmlFor="email">Correo electrónico</label>
                        <input className={`form-control ${errors.email ? 'is-invalid' : ''}`} type="email" name="email" id="email" placeholder="ejemplo@email.com" value={email} onChange={e => setEmail(e.target.value)}></input>
                        <div className="text-danger">{errors.email}</div>
                    </div>

                    <div className="d-flex gap-2">
                        <button type="submit" className="btn btn-success">Guardar</button>
                        <Link to="/" className='btn btn-secondary'>Cancelar</Link>
                    </div>
                </form>
            </div>
        </main>
    );
}
