import React from 'react';
import axios from "axios";
import {useNavigate} from "react-router-dom";
import jwtAxios from "../axios/jwtAxios";

const KakaoAuthHandler = () => {
    const code = new URL(window.location.href).searchParams.get("code")
    const navigate = useNavigate();
    axios.get("/users/login", {params: {code: code}})
        .then((res) => {
            const accessToken = res.data.accessToken;
            localStorage.setItem("accessToken", accessToken);
            navigate("/downbit");
    })

    return (
        <>

        </>
    );
};

export default KakaoAuthHandler;