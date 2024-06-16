import React, { useContext, useEffect } from "react";
import "./PaymentSuccess.css";
import { ShopContext } from "../../Context/ShopContext";

export const PaymentSuccess = () => {
  //need to clear cart
  const { clearCart } = useContext(ShopContext);

  useEffect(() => {
    clearCart();
  }, []);

  return (
    <div class="order-success">
      <div class="card">
        <div
          style={{
            borderRadius: "200px",
            height: "200px",
            width: "200px",
            background: "#F8FAF5",
            margin: "0 auto",
          }}
        >
          <i class="checkmark">✓</i>
        </div>
        <h1>Success</h1>
        <p>
          We received your purchase request;
          <br /> we'll be in touch shortly!
        </p>
      </div>
    </div>
  );
};
