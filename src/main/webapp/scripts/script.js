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

function searchEmployee() {
  const form = document.getElementById("searchFromEmployee");
  let emmployees = Array.from(
    document.getElementsByClassName("table__row_cells")
  );
  emmployees.forEach((element) => {
    let name = element.cells[0].innerText.toLowerCase();
    let role = element.cells[2].innerText;
    let isGood = false;
    if (form["manager"].checked) {
      isGood = isGood || role === "MANAGER";
    }
    if (form["cashier"].checked) {
      isGood = isGood || role === "CASHIER";
    }
    if (form["name"].value !== "") {
      let searchValue = form["name"].value.toLowerCase();
      isGood = isGood && name.includes(searchValue);
    }
    if (!isGood) {
      element.style.display = "none";
    } else {
      element.style.display = "table-row";
    }
  });
}

function searchCustomers() {
  const form = document.getElementById("searchFormCustomer");
  let emmployees = Array.from(
    document.getElementsByClassName("table__row_cells")
  );
  emmployees.forEach((element) => {
    let name = element.cells[0].innerText.toLowerCase();
    let phoneNumber = element.cells[1].innerText.toLowerCase();
    let cardNumber = element.cells[2].innerText.toLowerCase();
    let discount = parseInt(element.cells[4].innerText, 10);
    let isGood = true;
    if (form["name"].value !== "") {
      let searchValue = form["name"].value.toLowerCase();
      isGood =
        isGood &&
        (name.includes(searchValue) ||
          phoneNumber.includes(searchValue) ||
          cardNumber.includes(searchValue));
    }
    let from = form["from"].value;
    let to = form["to"].value;
    isGood = isGood && discount >= from;
    isGood = isGood && discount <= to;
    if (!isGood) {
      element.style.display = "none";
    } else {
      element.style.display = "table-row";
    }
  });
}

function searchProducts() {
  const form = document.getElementById("searchFormProduct");
  let products = Array.from(
    document.getElementsByClassName("table__row_cells")
  );
  let checkboxes = form["category"];
  let isCheckedSome = false;
  checkboxes.forEach((box) => {
    isCheckedSome = isCheckedSome || box.checked;
  });
  products.forEach((element) => {
    let category = element.cells[2].innerText.toLowerCase();
    let isGood = true;
    if (isCheckedSome) {
      isGood = false;
      for (let box of checkboxes) {
        if (box.checked && box.value.toLowerCase() === category) {
          isGood = true;
          break;
        }
      }
    }
    if (!isGood) {
      element.style.display = "none";
    } else {
      element.style.display = "table-row";
    }
  });
}

function searchProductsInStore() {
  const form = document.getElementById("searchFormProductInStore");
  let emmployees = Array.from(
    document.getElementsByClassName("table__row_cells")
  );
  emmployees.forEach((element) => {
    let name = element.cells[2].innerText.toLowerCase();
    let isGood = true;
    if (form["name"].value !== "") {
      let searchValue = form["name"].value.toLowerCase();
      isGood = isGood && name.includes(searchValue);
    }
    if (!isGood) {
      element.style.display = "none";
    } else {
      element.style.display = "table-row";
    }
  });
}

function setProductInStoreSearchForm() {
  let searchProductsInStoreForm = document.getElementById(
    "searchFormProductInStore"
  );
  let sort =
    getURLParameter("sort") == null ? "count" : getURLParameter("sort");
  let name = getURLParameter("name");
  let promotionalYes =
    getURLParameter("promotional-yes") === null ? false : true;
  let promotionalNo = getURLParameter("promotional-no") === null ? false : true;
  if (!promotionalNo && !promotionalYes) {
    promotionalNo = true;
    promotionalYes = true;
  }
  searchProductsInStoreForm["name"].value = name;
  searchProductsInStoreForm["sort"].value = sort;
  searchProductsInStoreForm["promotional-yes"].checked = promotionalYes;
  searchProductsInStoreForm["promotional-no"].checked = promotionalNo;
  let products = Array.from(
    document.getElementsByClassName("table__row_cells")
  );
  products.forEach((element) => {
    let isGood = false;
    let name = element.cells[2].innerText.toLowerCase();
    let UPC = element.cells[0].innerText.toLowerCase();
    let promotional =
      element.cells[1].innerText.toLowerCase() == "yes" ? true : false;
    if (promotionalNo) {
      isGood = isGood || !promotional;
    }
    if (promotionalYes) {
      isGood = isGood || promotional;
    }
    if (name !== null && name !== "") {
      let searchValue = searchProductsInStoreForm["name"].value.toLowerCase();
      isGood =
        isGood && (name.includes(searchValue) || UPC.includes(searchValue));
    }
    if (!isGood) {
      element.style.display = "none";
    } else {
      element.style.display = "table-row";
    }
  });
}

window.addEventListener("load", setProductInStoreSearchForm);

function setProductEditPromotionalListener() {
  let isPormotionalCheckbox = document.getElementById("isPromotionalCheckbox");
  let priceEdit = document.getElementById("priceEdit");
  if (!isPormotionalCheckbox.checked) {
    priceEdit.style.display = "inline-block";
    priceEdit.setAttribute("required", "true");
  } else {
    priceEdit.style.display = "none";
    priceEdit.removeAttribute("required");
  }
  isPormotionalCheckbox.addEventListener("change", () => {
    if (!isPormotionalCheckbox.checked) {
      priceEdit.style.display = "inline-block";
      priceEdit.setAttribute("required", "true");
    } else {
      priceEdit.style.display = "none";
      priceEdit.removeAttribute("required");
    }
  });
}

window.addEventListener("load", setProductEditPromotionalListener);

function hideCheckFilter() {
  let selector = document.getElementById("selectCheckFilter");
  let d1 = document.getElementById("1checkSearch");
  let d2 = document.getElementById("2checkSearch");
  let d3 = document.getElementById("3checkSearch");
  if (selector.value == "1") {
    d1.style.display = "block";
    d2.style.display = "none";
    d3.style.display = "none";
  } else if (selector.value == "2") {
    d1.style.display = "none";
    d2.style.display = "block";
    d3.style.display = "none";
  } else {
    d1.style.display = "none";
    d2.style.display = "none";
    d3.style.display = "block";
  }
}

function setCheckFilterTypeSelectorEvent() {
  let selector = document.getElementById("selectCheckFilter");
  hideCheckFilter();
  selector.addEventListener("change", hideCheckFilter);
}

window.addEventListener("load", setCheckFilterTypeSelectorEvent);

function setTotalSumOfChecks() {
  let totalPrice = 0;
  let totalVat = 0;
  let products = Array.from(
    document.getElementsByClassName("table__row_cells")
  );
  products.forEach((element) => {
    if (element.style.display != "none") {
      totalPrice += parseFloat(element.cells[4].innerText);
      totalVat += parseFloat(element.cells[5].innerText);
    }
  });
  document.getElementById("totalSumField").innerText =
    "Total: " + totalPrice.toFixed(4);
  document.getElementById("vatSumField").innerText =
    "VAT: " + totalVat.toFixed(4);
}

window.addEventListener("load", setTotalSumOfChecks);

function filterChecksByDate() {
  let from = document.getElementById("timeFromCheck").value;
  let to = document.getElementById("timeToCheck").value;
  from = from == "" ? null : new Date(from);
  to = to == "" ? null : new Date(to);
  console.log(from);
  console.log(to);
  let checks = Array.from(document.getElementsByClassName("table__row_cells"));
  checks.forEach((element) => {
    let date = new Date(element.cells[3].innerText);
    isGood = true;
    if (to !== null) {
      isGood = isGood && to >= date;
    }
    if (from !== null) {
      isGood = isGood && from <= date;
    }
    if (!isGood) {
      element.style.display = "none";
    } else {
      element.style.display = "table-row";
    }
  });
  setTotalSumOfChecks();
}
