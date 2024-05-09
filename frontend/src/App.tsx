import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import Register from './components/Auth/Register';

function App(): JSX.Element {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/register" element={<Register />} />
          {/* Aggiungi altre rotte qui come necessario */}
        </Routes>
      </div>
    </Router>
  );
}

export default App;
