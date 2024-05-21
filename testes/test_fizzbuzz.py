"""
TDD - Test Driven Development

Red
parte 1 -> Criar o teste e ver falhar
Green
parte 2 -> Criar o codigo e ver o teste passar


Refractor
parte 3 -> melhorar o codigo
"""
import unittest


class TestBaconOvos(unittest.TestCase):
    def test_bacon_com_ovos(self):
        with self.assertRaises(AssertionError):
            bacon_com_ovos(0)


unittest.main(verbosity=2)
