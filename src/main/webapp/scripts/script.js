function getURLParameter(name) {
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  return urlParams.get(name);
}

function getAmount(amount) {
  let amountToCart = parseInt(prompt("Input amount"), 10);
  let isGood = isNaN(amountToCart) ? false : true;
  if (!isGood) {
    alert("Input number please");
  } else if (isGood && (amountToCart > amount || amountToCart <= 0)) {
    isGood = false;
    alert("There aren't such amount");
  } else {
    let inputs = Array.from(
      document.getElementsByClassName("addCartAmountInput")
    );
    inputs.forEach((element) => {
      element.value = amountToCart;
    });
    return true;
  }
  return isGood;
}

var totalCartPrice = 0;
var VAT = 0;

function countTotal(percent) {
  let products = Array.from(
    document.getElementsByClassName("table__row_cells")
  );
  products.forEach((element) => {
    totalCartPrice +=
      parseFloat(element.cells[2].innerText).toFixed(4) *
      parseFloat(element.cells[3].innerText).toFixed(4);
  });
  totalCartPrice *= 100 - percent;
  totalCartPrice /= 100;
  VAT = totalCartPrice * 0.2;
}

function setTotal(percent) {
  countTotal(percent);
  setTotalPrice(totalCartPrice);
  setVAT(VAT);
  setTotalSum(totalCartPrice + VAT);
}

function setTotalPrice(price) {
  document.getElementById("total-price-item").innerText = price.toFixed(4);
}

function setVAT(price) {
  document.getElementById("vat-item").innerText = price.toFixed(4);
}

function setTotalSum(price) {
  document.getElementById("total-item").innerText = price.toFixed(4);
}

function checkEmptyCart() {
  let products = Array.from(
    document.getElementsByClassName("table__row_cells")
  );
  if (products.length == 0) {
    alert("Cart is empty");
    return false;
  }
  return true;
}
