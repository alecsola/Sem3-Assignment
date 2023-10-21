import './App.css';
import { Route, Routes, BrowserRouter as Router } from "react-router-dom";
import UserPage from './pages/UserPage';
import { LoginPage } from './pages/LoginPage';
import { UpdateUserPage } from './pages/UpdateUserPage';



function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<UserPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/update" element={<UpdateUserPage/>}/>
        </Routes>
      </Router>
    </div>  
  );
}
export default App;
