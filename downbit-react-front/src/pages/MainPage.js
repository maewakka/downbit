import React, {useEffect, useState} from 'react';
import NavBar from "../components/navBar/NavBar";
import jwtAxios from "../components/axios/jwtAxios";

const MainPage = () => {
    const [userInform, setUserInform] = useState({
        nickname: '',
        profileImage: ''
    })

    useEffect(() => {
        if(localStorage.getItem("accessToken") != null) {
            jwtAxios.get("/users/inform").then((res) => {
                const inform = res.data;
                setUserInform({
                    nickname: inform.nickname,
                    profileImage: inform.profileImage
                });
            })
        }
    }, [])

    return (
        <>
            <NavBar userInform={userInform}/>
        </>
    );
};

export default MainPage;