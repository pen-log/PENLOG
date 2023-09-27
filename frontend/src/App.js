import { useState } from 'react';
import './App.scss';
import { Routes, Route } from 'react-router-dom';
import Nav from './components/nav';
import Main from './pages/main';
import Search from './pages/search';
import Detail from './pages/detail';
import Signup from './pages/signup';
import Login from './pages/login';
import Write from './pages/write';
import User from './pages/user';
function App() {
  const [ismode, setIsmode] = useState(false);

  const darkMode = () => {
    setIsmode(!ismode);
  };

  return (
    <div className={`App ${ismode ? 'dark' : 'light'}`}>

      <Nav darkMode={darkMode} ismode={ismode} />
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/search" element={<Search />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/write" element={<Write />} />
        <Route path="/detail/:id" element={<Detail />} />
        <Route path="/user/:id" element={<User />} />
      </Routes>
    </div>
  );
}

export default App;
