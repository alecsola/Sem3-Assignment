import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import "./ConfirmPage.css";
import thumb from "../assets/thumbsup.png";

function ConfirmPage() {
  const [countdown, setCountdown] = useState(5);
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setInterval(() => {
      setCountdown((prevCountdown) => prevCountdown - 1);
    }, 1000);

    const redirectTimer = setTimeout(() => {
        navigate('/Event');
    }, 5000);

    return () => {
      clearInterval(timer);
      clearTimeout(redirectTimer);
    };
  }, []);

  return (
    <div className='confirmPage-content'>
      <h1 className='confirmPage-ThankYou-message'>Thank you!</h1>
      <img className='confirmPage-Image' src={thumb} alt='Duimpie'></img>
      <p className='confirmPage-Text'>Your purchase is successfully completed</p>
      <p className='confirmPage-Countdown'>Redirecting in {countdown} seconds...</p>
    </div>
  );
}

export default ConfirmPage;