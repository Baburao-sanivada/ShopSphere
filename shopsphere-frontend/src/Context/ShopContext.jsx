import React, { createContext, useEffect, useState } from "react";
import { Base_Url } from "../Utils";

export const ShopContext = createContext(null);

const ShopContextProvider = (props) => {
  const [products, setProducts] = useState([]);

  const getDefaultCart = () => {
    let cart = {};
    for (let i = 0; i < 300; i++) {
      cart[i] = 0;
    }
    return cart;
  };

  const [cartItems, setCartItems] = useState(getDefaultCart());

  useEffect(() => {
    fetch(`${Base_Url}/product/allProducts`)
      .then((res) => res.json())
      .then((data) => setProducts(data.Response));

    if (localStorage.getItem("auth-token")) {
      fetch(`${Base_Url}/cart/getCartData`, {
        method: "POST",
        headers: {
          "auth-token": `${localStorage.getItem("auth-token")}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(),
      })
        .then((resp) => resp.json())
        .then((data) => {
          if (data.success) {
            setCartItems(data.Response);
          }
        });
    }
  }, []);

  const getTotalCartAmount = () => {
    let totalAmount = 0;
    for (const item in cartItems) {
      if (cartItems[item] > 0) {
        let itemInfo = products.find((product) => product.id === Number(item));
        if (itemInfo) totalAmount += cartItems[item] * itemInfo.new_price;
      }
    }
    return totalAmount;
  };

  const getTotalCartItems = () => {
    let totalItem = 0;
    for (const item in cartItems) {
      if (cartItems[item] > 0) {
        totalItem += cartItems[item];
      }
    }
    return totalItem;
  };

  const addToCart = (itemId) => {
    setCartItems((prev) => ({ ...prev, [itemId]: prev[itemId] + 1 }));
    if (localStorage.getItem("auth-token")) {
      fetch(`${Base_Url}/cart/add/${itemId}`, {
        method: "POST",
        headers: {
          "auth-token": `${localStorage.getItem("auth-token")}`,
          "Content-Type": "application/json",
        },
      })
        .then((resp) => resp.json())
        .then((data) => {
          console.log(data);
        });
    }
  };

  const removeFromCart = (itemId) => {
    setCartItems((prev) => ({ ...prev, [itemId]: prev[itemId] - 1 }));
    if (localStorage.getItem("auth-token")) {
      fetch(`${Base_Url}/cart/remove/${itemId}`, {
        method: "POST",
        headers: {
          "auth-token": `${localStorage.getItem("auth-token")}`,
          "Content-Type": "application/json",
        },
      })
        .then((resp) => resp.json())
        .then((data) => {
          console.log(data);
        });
    }
  };

  const clearCart = async () => {
    let updatedCart = getDefaultCart();
    await fetch(`${Base_Url}/cart/clear`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "auth-token": `${localStorage.getItem("auth-token")}`,
      },
    })
      .then((resp) => resp.json())
      .then((data) => {
        console.log("Message From clearCart");
        if (data.success) console.log("Cart Cleared Successfully");
        setCartItems(updatedCart);
      });
  };

  const contextValue = {
    products,
    getTotalCartItems,
    cartItems,
    addToCart,
    removeFromCart,
    getTotalCartAmount,
    clearCart,
  };
  return (
    <ShopContext.Provider value={contextValue}>
      {props.children}
    </ShopContext.Provider>
  );
};

export default ShopContextProvider;
