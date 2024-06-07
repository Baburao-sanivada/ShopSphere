import React, { useEffect, useState } from "react";
import Hero from "../Components/Hero/Hero";
import Popular from "../Components/Popular/Popular";
import Offers from "../Components/Offers/Offers";
import NewCollections from "../Components/NewCollections/NewCollections";
import NewsLetter from "../Components/NewsLetter/NewsLetter";
import { Base_Url } from "../Utils";

const Shop = () => {
  const [popular, setPopular] = useState([]);
  const [newcollection, setNewCollection] = useState([]);

  const fetchInfo = () => {
    fetch(`${Base_Url}/product/allProducts`)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        setPopular(data.Response);
      });
    fetch(`${Base_Url}/product/newCollections`)
      .then((res) => res.json())
      .then((data) => setNewCollection(data.Response));
  };

  useEffect(() => {
    fetchInfo();
  }, []);

  return (
    <div>
      <Hero />
      <Popular data={popular} />
      <Offers />
      <NewCollections data={newcollection} />
      <NewsLetter />
    </div>
  );
};

export default Shop;
