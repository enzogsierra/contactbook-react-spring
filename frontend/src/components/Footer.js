import React from "react";
import { Link } from "react-router-dom";


export default function Footer()
{
    return (
        <footer className="mt-5 pt-4 pb-5 bg-dark text-white">
            <div className="container">
                <div className="pt-4 border-top border-secondary">
                    <div className="d-flex justify-content-between gap-4">
                        <p className="mb-0 fs-5 fw-semibold">Aplicación web diseñada por @enzogsierra</p>

                        <div className="d-flex justify-content-between gap-4">
                            <Link to="mailto:enzogsierra@gmail.com" className="fs-3 text-white text-decoration-none"><i className="bi bi-envelope-fill" title="Enviar email"></i></Link>
                            <Link to="https://github.com/enzogsierra" className="fs-3 text-white text-decoration-none"><i className="bi bi-github" title="Ver Github"></i></Link>
                            <Link to="https://www.linkedin.com/in/enzogsierra/" className="fs-3 text-white text-decoration-none"><i className="bi bi-linkedin" title="Ver LinkedIn"></i></Link>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    );
}
