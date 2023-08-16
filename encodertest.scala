package practice


import chisel3.tester._
import chisel3._
import org.scalatest._
import chisel3.experimental.BundleLiterals._

class encoderTest extends FreeSpec with ChiselScalatestTester{
    "Chisel Tester File" in{
        test(new encoder) { a =>
        a.io.in.poke("b1000".U) 
        a.clock.step(1)
        a.io.out.expect("b11".U)
        
         

        }
    }
}