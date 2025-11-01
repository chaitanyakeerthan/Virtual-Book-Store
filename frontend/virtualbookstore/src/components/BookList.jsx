import React, { useState, useEffect } from "react";
import "./BookList.css";
import axios from "axios";

const BookList = () => {
  const [books, setBooks] = useState([]);
  const [user, setUser] = useState(null);

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem("user"));
    if (storedUser) setUser(storedUser);

    const fetchBooks = async () => {
      try {
        const res = await axios.get("http://localhost:8080/api/books");
        setBooks(res.data);
      } catch (error) {
        console.error("Error fetching books", error);
      }
    };
    fetchBooks();
  }, []);
  

  const handleAddToCart = async (bookId) => {
    try {
      const user = JSON.parse(localStorage.getItem("user"));
      if (!user) {
        alert("Please login first");
        return;
      }

      await axios.post(
        `http://localhost:8080/api/cartItems/add?userId=${user.id}&bookId=${bookId}&quantity=1`
      );

      alert("Book added to cart successfully!");

    
      localStorage.setItem("cartUpdated", "true");

    } catch (error) {
      console.error("Error adding to cart:", error);
      alert("Failed to add to cart");
    }
  };

  return (
    <div className="book-container">
      {books.length === 0 ? (
        <p>Loading books...</p>
      ) : (
        books.map((book) => (
          <div className="book-card" key={book.id}>
            <h3>{book.title}</h3>
            <p><strong>Author:</strong> {book.author}</p>
            <p className="price">₹{book.price}</p>

            <div className="actions">
              <button className="add" onClick={() => handleAddToCart(book.id)}>Add to Cart</button>
            </div>
          </div>
        ))
      )}
    </div>
  );
};

export default BookList;
