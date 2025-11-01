import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Cart.css";
import Checkout from './Checkout.jsx';
import { Link, useNavigate } from 'react-router-dom';

const Cart = () => {
  const navigate=useNavigate();
  const [cartItems, setCartItems] = useState([]);
  const [totalPrice, setTotalPrice] = useState(0);
  const user = JSON.parse(localStorage.getItem("user"));

  const fetchCartItems = async () => {
    try {
      const res = await axios.get(
        `http://localhost:8080/api/cartItems/user/${user.id}`
      );
      setCartItems(res.data);

      const total = res.data.reduce(
        (sum, item) => sum + (item.book?.price || 0) * item.quantity,
        0
      );
      setTotalPrice(total);
    } catch (error) {
      console.error("Error fetching cart:", error);
    }
  };

  useEffect(() => {
    if (user) fetchCartItems();
  }, [user]);

  const increaseQuantity = async (itemId, currentQty) => {
    try {
      await axios.put(
        `http://localhost:8080/api/cartItems/update/${itemId}?userId=${user.id}&quantity=${
          currentQty + 1
        }`
      );
      fetchCartItems();
    } catch (error) {
      console.error("Error increasing quantity:", error);
    }
  };

  const decreaseQuantity = async (itemId, currentQty) => {
    if (currentQty <= 1) return;
    try {
      await axios.put(
        `http://localhost:8080/api/cartItems/update/${itemId}?userId=${user.id}&quantity=${
          currentQty - 1
        }`
      );
      fetchCartItems();
    } catch (error) {
      console.error("Error decreasing quantity:", error);
    }
  };

  const removeItem = async (itemId) => {
    try {
      await axios.delete(
        `http://localhost:8080/api/cartItems/remove/${itemId}?userId=${user.id}`
      );
      fetchCartItems();
    } catch (error) {
      console.error("Error removing item:", error);
    }
  };

  if (!user) return <h2>Please login to view your cart.</h2>;
  if (!cartItems || cartItems.length === 0)
    return <h3 className="empty-cart">Your Cart is Empty 🛒</h3>;

  const handleSubmit=()=>
  {
     navigate("/checkout");
  }

  return (
    <div className="cart-container">
      <h2>🛒 Your Cart</h2>

      <table className="cart-table">
        <thead>
          <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {cartItems.map((item) => (
            <tr key={item.id}>
              <td className="cart-item-title">{item.book?.title}</td>
              <td>{item.book?.author}</td>
              <td>{item.book?.category}</td>
              <td>₹{item.book?.price}</td>
              <td className="cart-item-quantity">
                <button
                  className="quantity-btn"
                  onClick={() => decreaseQuantity(item.id, item.quantity)}
                >
                  ➖
                </button>
                <span>{item.quantity}</span>
                <button
                  className="quantity-btn"
                  onClick={() => increaseQuantity(item.id, item.quantity)}
                >
                  ➕
                </button>
              </td>
              <td>₹{(item.book?.price || 0) * item.quantity}</td>
              <td>
                <button
                  className="remove-btn"
                  onClick={() => removeItem(item.id)}
                >
                  🗑️ Remove
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <div className="total-section">
        <h3>Total Price: ₹{totalPrice}</h3>
        <button className="checkout-btn" onClick={handleSubmit}>Proceed to Checkout </button>
      </div>
    </div>
  );
};

export default Cart;


