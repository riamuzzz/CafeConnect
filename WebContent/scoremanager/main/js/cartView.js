function calculateTotal() {
    let total = 0;

    // 各商品の行を取得
    const items = document.querySelectorAll('.item');
    items.forEach(item => {
        // 各商品の価格と数量を取得
        const index = item.getAttribute('data-index');
        const priceElement = item.querySelector(`.price[data-index="${index}"]`);
        const quantityElement = item.querySelector(`.quantity[data-index="${index}"]`);

        // 価格と数量の値を取得して計算
        const price = parseInt(priceElement.textContent, 10);
        const quantity = parseInt(quantityElement.value, 10);
        console.log(price);
        console.log(quantity)
        // 合計金額に加算
        total += price * quantity;
    });

    // 合計金額を更新
    document.getElementById('totalAmount').innerText = `合計金額: ${total}円`;
}
