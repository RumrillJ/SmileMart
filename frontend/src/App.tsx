import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Page } from './components/Pages/Page';
import { UserContext } from './contexts/UserContext';

export const backendUrl: string = "http://localhost:8080"

function App() {
	return (
		<div className="App">
			<header className="App-header"></header>

			<BrowserRouter>
				<UserContext.Provider value={null}>
					<Routes>
						<Route
							path=""
							element={
								<Page>
									<h2>Welcome</h2>
								</Page>
							}
						/>
						<Route path="/products" />
						<Route path="/user" />
					</Routes>
				</UserContext.Provider>
			</BrowserRouter>
		</div>
	)
}

export default App;
