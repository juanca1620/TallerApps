fun main() {
    println(decimalToBinary(13, ""))
}
fun decimalToBinary(aware: Int,binary: String): String{
    var awareFun = aware
    var residue = awareFun % 2
    awareFun = aware / 2

    if(aware == 0){
        return "$residue$binary"
    }else{
        return "${decimalToBinary(awareFun,binary)}$binary"
    }
}