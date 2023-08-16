//Write Chisel code for a standard RISCV ALU using switch, is statements.

//import ALUOP._
package practice
import chisel3._
import chisel3.util._


trait Config {
// word length configuration parameter
val WLEN = 32
// ALU operation control signal width
val ALUOP_SIG_LEN = 4
}


class alu extends Module with Config {
    val io = IO(new Bundle{
val in_A = Input (UInt(WLEN.W))
val in_B = Input (UInt(WLEN.W))
val alu_Op = Input ( UInt ( ALUOP_SIG_LEN . W ) )
val out = Output ( UInt ( WLEN . W ) )
val sum = Output ( UInt ( WLEN . W ) )
})

val sum1 = io . in_A + Mux( io . alu_Op (0) , - io . in_B , io . in_B )
val cmp = Mux( io . in_A ( WLEN -1) === io . in_B ( WLEN -1) , sum1 ( WLEN -1) ,
Mux( io . alu_Op (1) , io . in_B ( WLEN -1) , io . in_A ( WLEN -1) ) )
val shamt = io . in_B (4 ,0) . asUInt
val shin = Mux( io . alu_Op (3) , io . in_A , Reverse ( io . in_A ) )
val shiftr = ( Cat ( io . alu_Op (0) && shin ( WLEN -1) , shin ) . asSInt >> shamt ) (
WLEN -1 , 0)
val shiftl = Reverse ( shiftr )
io.out := 0.U
io.sum := 0.U

switch(io.alu_Op){
    is("b0000".U){
    io.out := sum1
    io.sum := sum1}

    is("b0001".U){
    io.out:=sum1
    io.sum := sum1}

    is("b0010".U){
    io.out:=cmp}

    is("b0011".U){
    io.out:=shiftr}

    is("b0100".U){
    io.out:=shiftl}

    is("b0101".U){
    io.out:=(io.in_A & io.in_B)}

    is("b0110".U){
    io.out:=(io.in_A | io.in_B)}

    is("b0111".U){
    io.out:=(io.in_A ^ io.in_B)}

    is("b1000".U){
    io.out:=io.in_A}

    is("b1001".U){
    io.out:=io.in_B}



}

}

//Mux( io . alu_Op === ALU_ADD . U || io . alu_Op === ALU_SUB .U , sum ,
// Mux( io . alu_Op === ALU_SLT . U || io . alu_Op === ALU_SLTU .U , cmp ,
// Mux( io . alu_Op === ALU_SRA . U || io . alu_Op === ALU_SRL .U , shiftr ,
// Mux( io . alu_Op === ALU_SLL .U , shiftl ,
// Mux( io . alu_Op === ALU_AND .U , ( io . in_A & io . in_B ) ,
// Mux( io . alu_Op === ALU_OR .U , ( io . in_A | io . in_B ) ,
// Mux( io . alu_Op === ALU_XOR .U , ( io . in_A ^ io . in_B ) ,
// Mux( io . alu_Op === ALU_COPY_A .U , io . in_A ,
// Mux( io . alu_Op === ALU_COPY_A .U , io . in_B , 0. U ) ) ) ) ) ) ) ) )

