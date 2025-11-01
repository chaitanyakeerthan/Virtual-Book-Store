import React, { useState } from 'react';
import './Login.css'
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';


const Login = () => {
  const Navigate=useNavigate();

  const[data,setData]=useState({email:'',password:''})

  const handleChange=(e)=>{
    setData({...data,[e.target.name]:e.target.value})
  }
  const handleSubmit=async (e)=>
  {
    e.preventDefault();
    try{
      const res=await axios.post('http://localhost:8080/api/users/login',data);
      if(res.status===200)
      {
        alert("Login Successful");
        localStorage.setItem("user", JSON.stringify(res.data));
        Navigate('/home')
        setData({email:'',password:''});
      }
    }
    catch(error)
    {
      alert("Invalid Credentials");
    }
  }

  return (
    <div className='container'>
    <h1>Login</h1>
    <form className='form' onSubmit={handleSubmit}>
    <input type="email" placeholder='Email Address' className='edit'name='email' value={data.email} onChange={handleChange}></input>
    <input type="password" placeholder='Password'className='edit' name='password'value={data.password} onChange={handleChange} ></input>
    <button type="submit"className='edit'>Submit</button>
    <Link to="/Register"className='edit'>Create an Account</Link>
    </form>
    </div>
  )
}

export default Login