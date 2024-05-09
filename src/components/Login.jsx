import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faEnvelope, faLock } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';


function Login() {
  const [action,setACtion]=useState("Sign Up")
  return (
    <div className="main-container">
    <div className='container'>
      <div className="header">
        <div className="text">{action}</div>
        <div className="underline"></div>
      </div>
      <div className="inputs">
        {action==="Login"?<div></div>: <div className="input">
          <FontAwesomeIcon className="login-icon" icon={faUser} /> 
          <input type="text" placeholder='user name'/>
        </div>}
       
        <div className="input">
          <FontAwesomeIcon className="login-icon" icon={faEnvelope} />        
          <input type="email" placeholder='email'/>
        </div>
        <div className="input">
          <FontAwesomeIcon className="login-icon" icon={faLock} /> 
          <input type="password " placeholder='password'/>
        </div>
      </div>
      {action==="Sign Up"?<div></div>:<div className="forgot-password">forgot password?<span>click here</span></div>
}
      <div className="submit-container">
        <div className={action=="Login"? "submit gray":"submit"} onClick={()=>{setACtion("Sign Up")}}> Sign-Up</div>
        <div className={action=="Sign Up"? "submit gray":"submit"} onClick={()=>{setACtion("Login")}}> <Link to='/pro'>Login</Link> </div>
      </div>
    </div>
    </div>
  );
}

export default Login;
