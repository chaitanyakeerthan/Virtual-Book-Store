import React, { useState } from 'react';
import './Register.css'
import { Link } from 'react-router-dom';
import axios from 'axios';
import Login from './Login.jsx';
import { useNavigate } from 'react-router-dom';


const Register = () => {
  const navigate=useNavigate();

  const[detail, setDetail]= useState(
    {name:'',email:'',password:''});
    
    const handleChange = (e) => {
    setDetail({ ...detail, [e.target.name]: e.target.value });
  };
  const handleSubmit= async (e)=>
  {
    e.preventDefault();
    try{
      await axios.post('http://localhost:8080/api/users/register',detail);
      alert("Regster successfully");
      navigate('/login')
      setDetail({name:'',email:'',password:''})
      
    }
    catch(error)
    {
      console.error('Register Failed');
      alert('failed to register');
    }
    
    
  }
  return (
    <div className='register'>

    <h1 className="header">Create Your Account</h1>

    <form className='form' onSubmit={handleSubmit} >

    <input type="text" placeholder='Username' className='edit'name='name' onChange={handleChange} value={detail.name}></input>

    <input type="email" placeholder='Email Address'name='email' className='edit'onChange={handleChange} value={detail.email}></input>

    <input type="password" placeholder='password'name='password' className='edit'onChange={handleChange} value={detail.password}></input>

    <button type="submit" className='edit'>Register</button>
    
    </form>
    </div>
  )
}

export default Register