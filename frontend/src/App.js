import './App.scss';
import { Routes, Route } from 'react-router-dom'
import { useState } from 'react';
import Nav from './components/nav';
import Main from './pages/main';
import Recent from './pages/recent';
import Search from './pages/search';
import Login from './components/login';
function App() {

  const [ismode, setIsmode] = useState(false)
  const darkMode = () => {
    setIsmode(!ismode)
  }
  return (
    <div className={`App ${ismode ? 'dark' : 'light'}`}>
      <Nav darkMode={darkMode} ismode={ismode} />
      <Routes>

        <Route path='/' element={<Main />} />
        <Route path='/recent' element={<Recent />} />
        <Route path='/search' element={<Search />} />
        <Route path='/login' element={<Login />} />

      </Routes>
    </div>
  );
}

export default App;
