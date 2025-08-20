import kotlin.math.pow

fun main() {
    println(decimalToBinary(10233, ""))
    println(binaryToDecimal("10011111111001",0))
}
fun decimalToBinary(aware: Int,binary: String): String{
    var awareFun = aware
    var binaryFun = binary
    var residue = awareFun % 2
    awareFun = aware / 2

    if(awareFun == 0){
        return "$residue$binary"
    }else{
        return "${decimalToBinary(awareFun,binary)}$residue$binary"
    }
}

fun binaryToDecimal (binary : String,decimal : Int):Int{
    if(binary.length == 1){
        var binaryNumber = binary.toInt()
        return binaryNumber + decimal
    }
    
    var currentDecimal = 2.0.pow(binary.length - 1).toInt()
    currentDecimal = currentDecimal * (binary[0].digitToInt())
    var currentBinary = binary.drop(1)
    return binaryToDecimal(currentBinary , currentDecimal + decimal)
}