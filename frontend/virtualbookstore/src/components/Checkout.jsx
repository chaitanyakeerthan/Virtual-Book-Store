import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Checkout.css";

const Checkout = () => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem("user"));

  const handleCheckout = async () => {
    if (!user || !user.id) {
      setError("User ID is missing. Please log in.");
      return;
    }

    try {
      setLoading(true);
      setError("");

      await axios.post(`http://localhost:8080/api/orders/place/${user.id}`);

      alert("✅ Order placed successfully!");
      navigate("/home"); 
    } catch (err) {
      console.error("Checkout failed:", err);
      setError("❌ Failed to place order. Try again later.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="checkout-container">
      <h2>Checkout 🧾</h2>
      {error && <p className="error-message">{error}</p>}
      <p>Review your cart and confirm your order.</p>
      <button
        className="checkout-btn"
        onClick={handleCheckout}
        disabled={loading}
      >
        {loading ? "Placing Order..." : "Confirm & Place Order"}
      </button>
    </div>
  );
};

export default Checkout;





