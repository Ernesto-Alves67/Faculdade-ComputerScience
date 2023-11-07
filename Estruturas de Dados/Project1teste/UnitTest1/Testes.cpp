#include "dstruct.h"
#include "CppUnitTest.h"

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace Testes
{
	/*TEST_CLASS(ListaSimples)
	{
	public:
		
		TEST_METHOD(TestConstructor)
		{
			
			int tamanho = 5; // Defina o tamanho desejado

			// Act
			Lista minhaLista(tamanho);

			// Assert
			Assert::AreEqual(minhaLista.getTamanho(), tamanho);  
			Assert::IsNull(minhaLista.getInicio());
			Assert::IsNull(minhaLista.getFim());
		}

		TEST_METHOD(tamanho) {

		}
	}; */

	TEST_CLASS(PilhaSimples)
	{
	public:

		TEST_METHOD(TestConstructor)
		{

			int tamanho = 5; // Defina o tamanho desejado

			// Act
			Pilha minhapilha(tamanho);

			// Assert
			Assert::AreEqual(minhapilha.getTamanho(), tamanho);
			Assert::AreEqual(minhapilha.getInicio(), NULL);
			Assert::AreEqual(minhapilha.getTopo(), NULL);
		}

		TEST_METHOD(Empilhar) {
			int tamanho = 5; // Defina o tamanho desejado

			// Act
			Pilha minhapilha(tamanho);
			for (int i = 0; i < 5; i++) {
				minhapilha.insereTopo(i);
				Assert::AreEqual(minhapilha.getTopo(), i);
			}
			
		}
		TEST_METHOD(desempilhar_errodeopção) {
			int tamanho = 5; // Defina o tamanho desejado

			// Act
			Pilha minhapilha(tamanho);
			for (int i = 0; i < 5; i++) {
				minhapilha.insereTopo(i);
				Assert::AreEqual(minhapilha.getTopo(), i);
			}
			for (int i = 0; i < 5; i++) {
				minhapilha.popTopo();
				Assert::AreEqual(minhapilha.getTopo(), i);
			}


		}

	};


}
