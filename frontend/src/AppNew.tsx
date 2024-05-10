import React, { useState } from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Login } from './components/Auth/Login';
import Register from './components/Auth/Register';
// import { Logout } from './components/Auth/Logout';
// import { Page } from './components/Pages/Page';
// import { UserContext } from './contexts/UserContext';
// import { ProductDetail } from './components/Product/ProductDetail';
// import { Checkout } from './components/Checkout/Checkout';

export const backend = (path?: string) => "http://localhost:8080" + (path ? "/" + path : "")

function App() {

	const [user, setUser] = useState({}) // Currently logged in user

	return (
		<Router>
			{/* <UserContext.Provider value={{ user, setUser }}> */}
				<div className="App">
					<header className="App-header"></header>
					<Routes>
						<Route path="/login" element={<Login />} />
						<Route path="/register" element={<Register />} />
						{/* <Route path="/" element={<Page><h2>Welcome</h2><h4>Click here to shop with us!</h4></Page>} />
						<Route path="/product/:productId" element={<ProductDetail />} />
						<Route path="/checkout" element={<Checkout />} /> */}
						<Route path="/cart" />
						<Route path="/profile" />
						{/* More routes can be added as needed */}
					</Routes>
				</div>
			{/* </UserContext.Provider> */}
		</Router>
	);
}

export default App;
