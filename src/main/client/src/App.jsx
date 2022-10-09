import {useEffect, useState} from 'react'
import './App.css'
import {HashRouter, Link, Route, Routes, useNavigate} from "react-router-dom";

function FrontPage() {
    return (
    <div>
        <h1>Best webshop off all time!!!</h1>
        <Link to={"/shop"}>Show all items</Link><br/>
        <Link to={"/addproduct"}>Add product</Link>
    </div>
    )
}

function ListAllProduct() {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(async () => {
            const res = await fetch("/api/products");
            setProducts(await res.json());
            setLoading(false);
        },[]);

    if (loading) {
        return (
            <div>Loading...</div>
        )
    }

    return (
        <div>
            <h1>Here are the shop!</h1>
            <ul>{products.map(p => <li>{p.name}</li>)}</ul>
        </div>
    )
}

function AddProduct() {
    const navigate = useNavigate();
    const [name, setName] = useState("");
    const [category, setCategory] = useState("");
    const [img, setImg] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState(0);
    const [stock, setStock] = useState(0);

    async function handleOnSubmit(event) {
        event.preventDefault();
        await fetch("/api/products", {
            method: "POST",
            body: JSON.stringify({name, category, img, description, price, stock}),
            headers: {
               "Content-Type": "application/json"
            }
        });

        navigate("..");
    }

    return (
        <div>
            <h1>Add product to the store!</h1>

            <form onSubmit={handleOnSubmit}>
                <input value={name} type="text" onChange={(e) => setName(e.target.value)}/>
                <input value={category} type="text" onChange={(e) => setCategory(e.target.value)}/>
                <input value={img} type="text" onChange={(e) => setImg(e.target.value)}/>
                <input value={description} type="text" onChange={(e) => setDescription(e.target.value)}/>
                <input value={price} type="number" onChange={(e) => setPrice(parseInt(e.target.value))}/>
                <input value={stock} type="number" onChange={(e) => setStock(parseInt(e.target.value))}/>
                <button>Add new Product</button>
            </form>

        </div>
    )
}

function App() {

    return (
    <div className="App">
        <HashRouter>
            <Routes>
                <Route path={"/"} element={<FrontPage />} />
                <Route path={"/shop"} element={<ListAllProduct />}/>
                <Route path={"/addproduct"} element={<AddProduct />}/>
            </Routes>
        </HashRouter>
    </div>
    )

}

export default App
