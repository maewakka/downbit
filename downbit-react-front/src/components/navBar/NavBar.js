import React from 'react';
import {KAKAO_AUTH_URL, KAKAO_LOGOUT_URL} from "../login/kakaoAuth";

const NavBar = () => {
    const onClickLogin = () => {
        window.location.href = KAKAO_AUTH_URL;
    }
    const onClickLogout = () => {
        localStorage.removeItem("accessToken")
        window.location.href = KAKAO_LOGOUT_URL;
    }

    return (
        <nav className="navbar navbar-expand-lg bg-light">
            <div className="container-fluid">
                <a className="navbar-brand" href="#">DownBit</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item" style={{cursor: 'pointer'}}>
                            <a className="nav-link">거래소</a>
                        </li>
                        <li className="nav-item" style={{cursor: 'pointer'}}>
                            <a className="nav-link">입출금</a>
                        </li>
                        <li className="nav-item" style={{cursor: 'pointer'}}>
                            <a className="nav-link">투자내역</a>
                        </li>
                        <li className="nav-item" style={{cursor: 'pointer'}}>
                            <a className="nav-link">대회</a>
                        </li>
                    </ul>
                </div>
                <div>
                    {
                        localStorage.getItem("accessToken") === null ?
                            <button type="button" className="btn btn-outline-success" onClick={onClickLogin}>로그인</button>
                            :
                            <button type="button" className="btn btn-outline-success" onClick={onClickLogout}>로그아웃</button>
                    }
                </div>
            </div>
        </nav>
    );
};

export default NavBar;