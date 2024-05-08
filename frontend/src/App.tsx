import React, { useState } from 'react';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Page } from './components/Pages/Page';
import { UserContext } from './contexts/UserContext';
import { ProductDetail } from './components/Product/ProductDetail';

export const backend = (path?: string) => "http://localhost:8080" + path ? "/" + path : ""

function App() {

	const [user, setUser] = useState({}) // Currently logged in user

	return (
		<div className="App">
			<header className="App-header"></header>

			<BrowserRouter>
				<UserContext.Provider value={{ user: user, setUser: setUser }}>
					<Routes>
						<Route
							path=""
							element={
								<Page>
									<h2>Welcome</h2>
								</Page>
							}
						/>
						<Route path="/register" />
						<Route path="/login" />
						<Route path="/products" />
						<Route
							path="/product/:productId"
							element={<ProductDetail />}
						/>
						<Route path="/cart" />
						<Route path="/checkout" />
						<Route path="/profile" />
					</Routes>
				</UserContext.Provider>
			</BrowserRouter>
		</div>
	)
}

export default App;
