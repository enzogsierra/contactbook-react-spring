import React from 'react';
import { Link } from 'react-router-dom';


export default function Header()
{
    return (
        <header className="mb-4 p-4 text-bg-dark">
            <div className="container">
                <div className="d-flex align-items-center justify-content-between gap-4">
                    <Link to="/" className="text-white text-decoration-none">
                        <h1 className="d-flex align-items-center gap-3">
                            <i className="bi bi-journal-bookmark"></i>
                            Inicio
                        </h1>
                    </Link>

                    <div className="d-flex align-items-center justify-content-between gap-4">
                        <Link to="https://github.com/enzogsierra" className="btn btn-outline-light fw-semibold" title="Ir a Github"><i className="bi bi-github"></i> Github</Link>
                        <Link to="https://github.com/enzogsierra/contactbook-react-springboot" className="btn btn-outline-light fw-semibold" title="Ver código fuente"><i className="bi bi-code-slash"></i> Código fuente</Link>
                    </div>
                </div>
            </div>
        </header>
    );
}
