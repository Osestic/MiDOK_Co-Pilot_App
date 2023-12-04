import {Routes, Route} from 'react-router-dom';
import './styles/App.css'
import HomePage from './pages/HomePage';
import UserPage from './pages/UserPage';
import HealthInfoPage from './pages/HealthInfoPage';
import MiDOKSearchPage from './pages/MiDOKSearchPage';
import DiabetesPage from './pages/DiabetesPage';
import MiDOKPage from './pages/MiDOKPage';

function App() {

  return (
    <>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/user/:id" element={<UserPage />} />
        <Route path="/healthinfo" element={<HealthInfoPage />} />
        <Route path="/MiDOK-Search/:id" element={<MiDOKSearchPage />} />
        <Route path="/diabetes" element={<DiabetesPage />} />
        <Route path="/MiDOK" element={<MiDOKPage />} />
      </Routes>
    </>
  )
}

export default App
