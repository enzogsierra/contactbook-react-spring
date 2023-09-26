import axios from "axios";

const CONTACT_REST_API_BASE_URL = "http://localhost:8080/api/v1/"; // Url base de la API de contactos

class ContactService
{
    findAll() { // Obtener todos los contactos
        return axios.get(CONTACT_REST_API_BASE_URL);
    }

    findById(id) { // Buscar contacto por id
        return axios.get(CONTACT_REST_API_BASE_URL + id);
    }

    save(contact) { // Guardar contacto
        return axios.post(CONTACT_REST_API_BASE_URL + "save", contact);
    }

    deleteById(id) { // Eliminar contacto por id
        return axios.delete(CONTACT_REST_API_BASE_URL + "delete/" + id);
    }
}

const contactServiceInstance = new ContactService();
export default contactServiceInstance;