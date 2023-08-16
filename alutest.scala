package practice


import chisel3.tester._
import chisel3._
import org.scalatest._
import chisel3.experimental.BundleLiterals._

class ALUtest extends FreeSpec with ChiselScalatestTester{
    "Chisel Tester File" in{
        test(new alu) { a =>
        a.io.in_A.poke(24.U)
        a.io.in_B.poke(24.U)
        a.io.alu_Op.poke("b0001".U)
        a.io.out.expect(0.U)
        a.io.sum.expect(0.U)


    }
}
}
