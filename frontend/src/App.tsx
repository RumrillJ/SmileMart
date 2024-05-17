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
import { CartPage } from './components/Pages/CartPage';
import { CheckoutPage } from './components/Pages/CheckoutPage';
import { ProfilePage } from './components/Pages/ProfilePage';
import { AddProductPage } from './components/Pages/AddProductPage';
import { OrdersPage } from './components/Pages/OrdersPage';

export const backend = (path?: string) => "http://localhost:8080" + (path ?? "");

function App() {

    return (
		<div className="App">
			<header className="App-header"></header>

			<BrowserRouter>
				<UserProvider>
					<CartProvider>
						<Routes>
							<Route path="" element={<ProductsPage />} />
							<Route path="/register" element={<Register />} />
							<Route path="/login" element={<Login />} />
							<Route
								path="/product/:productId"
								element={<ProductDetail />}
							/>
							<Route path="/cart" element={<CartPage />} />
							<Route
								path="/checkout"
								element={<CheckoutPage />}
							/>
							<Route path="/profile" element={<ProfilePage />} />
							<Route path="/orders" element={<OrdersPage/>}/>
							<Route
								path="/add-product"
								element={<AddProductPage />}
							/>
						</Routes>
					</CartProvider>
				</UserProvider>
			</BrowserRouter>
		</div>
	)
}

export default App;
