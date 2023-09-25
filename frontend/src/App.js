import './App.css';

function doSomething(e)
{
    e.preventDefault();
    const v1 = parseInt(e.target.value1.value);
    const v2 = parseInt(e.target.value2.value);
    const sum = v1 + v2;

    alert("Sum is:" + sum);
}

function App() 
{
    return (
        <div>
            <form onSubmit={doSomething}>
                <p>Type first value: </p>
                <input type="text" name="value1"></input>

                <p>Type first value: </p>
                <input type="text" name="value2"></input>

                <input type="submit"></input>
            </form>
        </div>
    );
}

export default App;
