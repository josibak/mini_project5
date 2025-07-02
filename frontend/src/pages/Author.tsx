
import Header from '@/components/Header';
import Footer from '@/components/Footer';

const Author = () => {
  return (
    <div className="min-h-screen bg-gray-50">
      <Header />
      
      <main className="pt-24 pb-16">
        <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center">
            <h1 className="text-3xl md:text-4xl font-light text-gray-900 mb-4">
              작가 페이지
            </h1>
            <p className="text-lg text-gray-600 mb-8">
              작가 관련 기능들이 준비 중입니다.
            </p>
            
            <div className="bg-white rounded-lg border border-gray-200 p-8">
              <p className="text-gray-500">
                작가 프로필, 작품 목록, 독자와의 소통 등의 기능이 곧 추가될 예정입니다.
              </p>
            </div>
          </div>
        </div>
      </main>
      
      <Footer />
    </div>
  );
};

export default Author;
