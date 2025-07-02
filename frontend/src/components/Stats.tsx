
import { BookOpen, Users, Sparkles } from 'lucide-react';
import { Link } from 'react-router-dom';

const Stats = () => {
  return (
    <section className="py-20 px-4 sm:px-6 lg:px-8 bg-gray-50">
      <div className="max-w-6xl mx-auto">
        <div className="text-center mb-16">
          <h2 className="text-3xl md:text-4xl font-light text-gray-900 mb-4">
            AI 도서 생성 플랫폼
          </h2>
          <p className="text-lg text-gray-600">
            AI가 도서 표지를 생성하고 요약하여 출판까지 도와드립니다
          </p>
        </div>
        
        <div className="grid md:grid-cols-3 gap-8 mb-12">
          <div className="text-center">
            <div className="inline-flex items-center justify-center w-16 h-16 bg-amber-100 rounded-full mb-4">
              <BookOpen className="w-8 h-8 text-amber-700" />
            </div>
            <div className="text-3xl font-light text-amber-700 mb-2">156권</div>
            <div className="text-gray-600">AI 생성 도서</div>
          </div>
          
          <div className="text-center">
            <div className="inline-flex items-center justify-center w-16 h-16 bg-amber-100 rounded-full mb-4">
              <Sparkles className="w-8 h-8 text-amber-700" />
            </div>
            <div className="text-3xl font-light text-amber-700 mb-2">42개</div>
            <div className="text-gray-600">AI 표지 생성</div>
          </div>
          
          <div className="text-center">
            <div className="inline-flex items-center justify-center w-16 h-16 bg-amber-100 rounded-full mb-4">
              <Users className="w-8 h-8 text-amber-700" />
            </div>
            <div className="text-3xl font-light text-amber-700 mb-2">85명</div>
            <div className="text-gray-600">활동 작가</div>
          </div>
        </div>
        
        <div className="bg-white rounded-lg p-8 border border-gray-200">
          <div className="flex items-center gap-3 mb-6">
            <Users className="w-6 h-6 text-amber-700" />
            <h3 className="text-xl font-medium text-gray-900">작가로 활동하기</h3>
          </div>
          
          <p className="text-gray-600 mb-6">
            작가로 활동하고 싶으시다면 작가 등록을 통해 시작해보세요
          </p>
          
          <div className="text-center">
            <p className="text-gray-500 mb-4">지금 바로 시작하세요</p>
            <Link to="/author-registration">
              <button className="px-6 py-3 bg-amber-700 text-white rounded-lg hover:bg-amber-600 transition-colors duration-200 font-medium">
                작가 등록하기
              </button>
            </Link>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Stats;
