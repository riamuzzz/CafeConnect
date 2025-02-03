document.addEventListener("DOMContentLoaded", function () {
    calculateTotal(); // ページ読み込み時に合計金額を計算
});

function calculateTotal() {
    let total = 0;

    // 全商品の価格と数量を取得して合計金額を計算
    const items = document.querySelectorAll('.item');
    items.forEach(item => {
        const index = item.getAttribute('data-index');
        const priceElement = item.querySelector(`.price[data-index="${index}"]`);
        const quantityElement = item.querySelector(`.quantity[data-index="${index}"]`);

        const price = parseInt(priceElement.textContent, 10) || 0; // 商品価格
        const quantity = parseInt(quantityElement.value, 10) || 0; // 選択された数量

        total += price * quantity;
    });

    // 合計金額を更新
    document.getElementById('totalAmount').innerText = `${total}`;
}

function changeAction(action) {
    // フォームのaction属性を動的に変更
    document.getElementById('myForm').action = action;
}