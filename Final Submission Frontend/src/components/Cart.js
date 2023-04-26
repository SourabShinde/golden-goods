import React,{useState,useEffect} from "react";
import axios from 'axios';
import {Table} from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import {toast} from "react-toastify";



function Cart(){
    const [quantity,setQuantity]=useState(0)
    const [totalBill,setTotalBill]=useState(0)
    const [productId,setProductId]=useState(0)
    const [cartData,setCartData]=useState([])
    const [uId,setUId]=useState(0);
    const navigate = useNavigate();

    // setProductId={subProduct.id}
    // setQuantity(cart.quantity)
    // {setTotalBill(cart.totalBill)}

    let token=sessionStorage.getItem('user');
    const userId=sessionStorage.getItem('userId');
    
    const config = {
        withCredentials: false,
        headers: { Authorization: `Bearer ${token}`,   
        'Accept' : 'application/json',
        'Content-Type': 'application/json' }
    };
    
    useEffect(()=>{
        getCartItems();
    },[]);

    const handleDeleteItem=(productId)=> {
        axios.delete(`http://localhost:8080/carts/${productId}`,config).then(
            (response)=>{
                toast.success("Product deleted sucessfully!!");
                window.location.reload();
            },
            (error)=>{
                toast.error("Server error/insufficient privileges");
            }
        )

       
    }

    const getCartItems=()=>{
        axios.get("http://localhost:8080/carts/"+userId+"/cart" ,config)
        .then((response)=>{
                    
        setCartData(response.data)
        console.log(response.data)
        
        },
        (error)=>{console.log(error)
        console.log(token);
    })}

    


    

    const navigateOrderItems=()=>{
        //quantity,product id,userid,total bill
        
        var postItemInOrderItem={productId,quantity,totalBill,uId};
        axios.post("http://localhost:8080/orderItems/"+userId+"/addOrderItem" ,postItemInOrderItem,config)
        console.log(totalBill)
        .then((response)=>setCartData(response.data))
        .then((error)=>console.log(error));
        navigate('/orderitems')
    }

//    const updateQuantity=()=>{
//     axios.put("http://localhost:9092/carts/update" ,config)
//     .then((response)=>setCartData(response.data))
//     .then((error)=>console.log(error));  
//    } 
    return(
      <React.Fragment>
        <div className="container-fluid cart">
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                <div className="container-fluid" style={{marginLeft:"3rem"}}>
                    <a className="navbar-brand" href="/">SHOPPING CART</a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent" style={{marginLeft:"22rem"}}>
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                        <a className="nav-link " aria-current="page" href="/">Home</a>
                        </li>
                        <li className="nav-item">
                        <a className="nav-link active " aria-current="page" href="/category">Categories</a>
                        </li>

                        <li className="nav-item">
                        <a className="nav-link" href="/about">About Us</a>
                        </li>
                        <li className="nav-item">
                        <a className="nav-link" href="/contact">Contact Us</a>
                        </li>
                        

                        <div className='buttons' style={{marginLeft:"20rem"}}>
                            <a href='/login-customer' className='btn btn-outline-light'>
                                <i className='text-white '>Login  &#187;</i>
                            </a>
                            <a href='/register-customer' className='btn btn-outline-light ms-3'>
                                <i className='text-white '>Register &#187;</i>
                            </a>
                            <a href='/customer-cart' className='btn btn-outline-light ms-3'>
                                <i className='text-white '>Cart &#187;</i>
                            </a>
                        </div>
                        
                        
                    </ul>
                    
                    </div>
                </div>
            </nav>
        <h1  className="text-center my-2" style={{color:"Black"}}>My Cart</h1>
        <table className="table table-dark table-striped">
            <thead>
                <tr>
                <th>Product Name</th>
                <th>Product Image</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total Price</th>
                <th>Buy Now</th>
                <th>Delete</th>
                </tr>
            </thead>
            <tbody>
            {cartData.map(cart=>{
                        return(
                             
                                    <tr>
                                        {/* <td><img src={subProduct.productImagePath} alt='l' height={200} width={150}/></td> */}
                                        <td><b>{cart.productId.productName}</b></td>
                                        <td><img src={cart.productId.productImagePath} alt='l' height={50} width={50}/></td>
                    
                                        <td>&#8377; {cart.productId.sellingPrice}</td>
                    
                                
                                        <td><div >
                                                {/* <button type="button"  onClick={()=>handleDecrement(cart.id)} className="input-group-text" >-</button> */}
                                                   <input type="number" className="form-control text-center" value={cart.quantity}  />
                                                {/* <button type="button" onClick={()=>handleIncrement(cart.id)} className="input-group-text" >+</button> */}
                                            </div></td>
                                            <td><input type='number' id='totalPrice' className="form-control" value={cart.productId.sellingPrice*cart.quantity} ></input></td> 
                                            <td><button type="button" className="btn btn-warning button-cool" ><a href="/myorder">Buy Now</a></button></td>
                                            <td><button className="btn btn-danger button-cool" onClick={()=>handleDeleteItem(cart.id)}>Delete</button></td>
                                    </tr>        
                        )})}
                    
            </tbody>
        </table>
        </div>
     </React.Fragment>
    )


}

export default Cart;