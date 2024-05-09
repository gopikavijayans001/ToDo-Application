import React from 'react';
import './App.css';
import {BrowserRouter as Router,Routes,Route,Link} from "react-router-dom"
import Todo from './components/Todo';
import Project from './components/Project';
import Login from './components/Login';

function App() {
  return (
    <Router>
         
       {/* <Link to='/project'></Link>
       <Link to='/todos'></Link> */}
  
     <Routes>
     <Route path="/" element={<Login/>} />
     <Route path='/pro' element={<Project/>}/>
      <Route path="/todos" element={<Todo/>} /> 
     </Routes>
    </Router>

  );
}

export default App;
