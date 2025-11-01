
import React from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Navbar.css";

const Navbar = () => {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem("user"));

  const handleLogout = () => {
    localStorage.removeItem("user");
    navigate("/login");
  };

  return (
    <nav className="navbar">
      <div className="nav-left">
        <h2 className="logo">📚 Virtual BookStore</h2>
      </div>

      <div className="nav-right">
        <Link to="/home" className="nav-link">Home</Link>
        <Link to="/cart" className="nav-link">🛒 Cart</Link>

        {user ? (
          <>
            <span className="username">Hi, {user.name}</span>
            <button className="logout-btn" onClick={handleLogout}>Logout</button>
          </>
        ) : (
          <Link to="/login" className="nav-link">Login</Link>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
