import { useEffect, useState } from "react";
import { useUser } from "../../contexts/UserContext";
import { OrderInterface } from "../../interfaces/OrderInterface";
import {
  getAllOrders,
  getUserOrders,
  updateOrderStatus,
} from "../../api/orderAPI";
import axios from "axios";
import { backend } from "../../App";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import "../../styles/orders.css";

export const Orders: React.FC = () => {
  const [orders, setOrders] = useState([] as OrderInterface[]);
  const [status, setStatus] = useState<string>("Shipped");

  const navigate = useNavigate();

  async function getOrders() {
    if (user != null) {
      if (user.role == "USER") {
        const response = await axios.get(backend("/orders/user"), {
          withCredentials: true,
          headers: {
            Authorization: "Bearer " + user.token,
          },
        });
        setOrders(response.data);

        console.log("got user order" + response.data);
      } else if (user.role == "ADMIN") {
        const response = await axios.get(backend("/orders"), {
          withCredentials: true,
          headers: {
            Authorization: "Bearer " + user.token,
          },
        });
        setOrders(response.data);
      }
    }
  }

  async function changeStatus(order: OrderInterface, newStatus: string) {
    const response = await axios.patch(
      backend("/orders/" + order.orderId + "/" + newStatus),
      {
        orderId: order.orderId,
        date: order.date,
        statusId: order.status.statusId,
        userId: order.user.userId,
      },
      {
        withCredentials: true,
        headers: {
          Authorization: "Bearer " + user!.token,
        },
      }
    );
    toast.info("Status Updated!");
    navigate("/");
  }

  const storeStatus = (input: any) => {
    setStatus(input.target.value);
  };

  const { user } = useUser();

  useEffect(() => {
    getOrders();
  }, []);

  console.log(orders);

  const ordersTsx =
    user!.role !== "ADMIN"
      ? orders.map((order, index) => {
          let productTsx = order.products.map((product, key) => {
            return (
              <tr key={key}>
                <td>{product.product.name}</td>
                <td>${product.product.cost}</td>
                <td>{product.product.category.description}</td>
                <td>{product.product.description}</td>
              </tr>
            );
          });
          return (
            <div key={index} className="orders">
              <h4 className="header-container">
                <span>Order ID: {order.orderId}</span>
                <span>Status: {order.status.statusId}</span></h4>
              <div className="order-container">
                <table>
                  <thead>
                    <tr>
                      <th>Name</th>
                      <th>Price</th>
                      <th>Category</th>
                      <th>Description</th>
                    </tr>
                  </thead>
                  <tbody>{productTsx}</tbody>
                </table>
              </div>
            </div>
          );
        })
      : orders.map((order, index) => {
          return (
            <div key={index} className="admin-order-container">
              <h4>Order ID: {order.orderId}</h4>
              
              <select
                name="Status Filter"
                id="status-filter"
                value={order.status.statusId}
                onChange={storeStatus}
              >
                <option value="Processing">Processing</option>
                <option value="Shipped">Shipped</option>
                <option value="Refunded">Refunded</option>
              </select>
              <button onClick={() => changeStatus(order, status)}>
                Change Status
              </button>
            </div>
          );
        });

  //I cannot return the time/date as we have it
  return <div>{ordersTsx}</div>;
};
