import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import { Login } from './components/Auth/Login';
// import { Logout } from './components/Auth/Logout';
import Register from './components/Auth/Register';

export const backend = (path?: string) => "http://localhost:8080" + path ? "/" + path : ""

function App(): JSX.Element {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/login" element={<Login />} />
          {/* <Route path="/logout" element={<Logout />} /> */}
          <Route path="/register" element={<Register />} />
          {/* Ass more routes here */}
        </Routes>
      </div>
    </Router>
  );
}

export default App;