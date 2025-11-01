import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Register from './components/Register';
import Login from './components/Login';
import Home from './components/Home';
import BookList from './components/BookList';
import Cart from './components/Cart'
import Checkout from './components/Checkout'

function App() {
  return (
   
      <div className="App">
        <Routes>
          <Route path="/register" element={<Register />} />
           <Route path="/cart" element={<Cart />} />
          <Route path="/login" element={<Login />} />
          <Route path="/" element={<Login />} /> 
          <Route path="/home" element={<Home />} />
          <Route path="/bookList" element={<BookList />} />
          <Route path="/checkout" element={<Checkout />} />
        </Routes>
      </div>
    
  );
}

export default App;
