import './App.css';
import { Route, Routes, BrowserRouter as Router } from "react-router-dom";
import UserPage from './pages/UserPage';



function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<UserPage />} />
        </Routes>
      </Router>
    </div>  
  );
}
export default App;
