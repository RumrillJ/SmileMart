import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import { Page } from './components/Pages/Page';
import { UserProvider } from "./contexts/UserContext"
import { ProductDetail } from './components/Product/ProductDetail';
import { Checkout } from './components/Checkout/Checkout';
import Login from './components/Auth/Login';
// import { Logout } from './components/Auth/Logout';
import Register from './components/Auth/Register';
import { AddProduct } from './components/Product/AddProduct';
import { ProductInterface } from './interfaces/ProductInterface';
import { CartProvider } from './contexts/CartContext';
import { ProductItem } from './components/Product/ProductItem';
import { Cart } from './components/Checkout/Cart';
import { ProductsPage } from './components/Pages/ProductsPage';

export const backend = (path?: string) => "http://localhost:8080" + (path ?? "");

function App() {

    return (
		<div className="App">
			<header className="App-header"></header>

			<BrowserRouter>
				<UserProvider>
					<CartProvider>
						<Routes>
							<Route
								path=""
								element={<ProductsPage/>}
							/>
							<Route path="/register" element={<Register />} />
							<Route path="/login" element={<Login />} />
							<Route
								path="/product/:productId"
								element={<ProductDetail />}
							/>
							<Route path="/cart" element={<Cart/>}/>
							<Route path="/checkout" element={<Checkout />} />
							<Route path="/profile" />

							{/* Just testing components here...*/}
							<Route
								path="/testAddProduct"
								element={
									<AddProduct
										onSubmit={(p) => console.log(p)}
									/>
								}
							/>

							<Route
								path="/testCart"
								element={
									<Page>
										<ProductItem
											product={{
												productId: 5,
												name: "big shoes",
												description:
													"comically large shoes",
												category: "shoes",
												price: 19.99,
											}}
										/>
										<ProductItem
											product={{
												productId: 6,
												name: "big hat",
												description: "a funny hat",
												category: "headwear",
												price: 9.99,
											}}
										/>
										<Cart />
									</Page>
								}
							/>
						</Routes>
					</CartProvider>
				</UserProvider>
			</BrowserRouter>
		</div>
	)
}

export default App;
