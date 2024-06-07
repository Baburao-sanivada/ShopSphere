import React from "react";
import "./Popular.css";
import Item from "../Item/Item";
import { Base_Url } from "../../Utils";

const Popular = (props) => {
  // console.log("Popular");
  // console.log(`${Base_Url}${props.data.image}`);
  return (
    <div className="popular">
      <h1>POPULAR IN WOMEN</h1>
      <hr />
      <div className="popular-item">
        {props.data.map((item, i) => {
          return (
            <Item
              id={item.id}
              key={i}
              name={item.name}
              image={`${Base_Url}${item.image}`}
              new_price={item.new_price}
              old_price={item.old_price}
            />
          );
        })}
      </div>
    </div>
  );
};

export default Popular;
