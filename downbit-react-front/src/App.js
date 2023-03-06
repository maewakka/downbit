import React from 'react';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import MainPage from "./pages/MainPage";
import KakaoAuthHandler from "./components/login/KakaoAuthHandler";

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/downbit" element={<MainPage/>}/>
                <Route path="/user/kakao/callback" element={<KakaoAuthHandler/>}/>
            </Routes>
        </Router>
    );
};

export default App;