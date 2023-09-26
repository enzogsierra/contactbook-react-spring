import { BrowserRouter, Route, Routes } from "react-router-dom";
import Footer from "./components/Footer";
import Header from "./components/Header";
import Home from "./views/Home";
import ContactForm from "./views/ContactForm";


function App() 
{
    return (
        <div>
            <BrowserRouter>
                <Header></Header>

                <div className="container">
                    <Routes>
                        <Route exact path="/" element={<Home/>}></Route>
                        <Route path="/form" element={<ContactForm/>}></Route>
                        <Route path="/form/:id" element={<ContactForm/>}></Route>
                    </Routes>
                </div>

                <Footer></Footer>
            </BrowserRouter>
        </div>
    );
}

export default App;
