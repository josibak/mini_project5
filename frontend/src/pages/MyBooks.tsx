
import { useState } from 'react';
import { Link } from 'react-router-dom';
import { Edit, Send, Plus } from 'lucide-react';
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import { Button } from '@/components/ui/button';
import { useToast } from '@/hooks/use-toast';

const MyBooks = () => {
  const { toast } = useToast();
  
  const books = [
    {
      id: 1,
      title: "달빛 아래의 서약",
      status: "출간 완료",
      statusColor: "green",
      lastUpdate: "2024-12-20",
      wordCount: "45,000자",
      views: "127"
    },
    {
      id: 2,
      title: "미완성된 이야기",
      status: "최종저장",
      statusColor: "blue",
      lastUpdate: "2024-12-18",
      wordCount: "32,000자",
      views: ""
    },
    {
      id: 3,
      title: "새로운 시작",
      status: "임시저장",
      statusColor: "gray",
      lastUpdate: "2024-12-15",
      wordCount: "15,000자",
      views: ""
    },
    {
      id: 4,
      title: "시간의 정원사",
      status: "출간 요청됨",
      statusColor: "orange",
      lastUpdate: "2024-12-10",
      wordCount: "55,000자",
      views: ""
    }
  ];

  const handlePublishRequest = () => {
    toast({
      title: "AI 출간 작업 처리 중",
      description: "AI가 출간 작업을 처리하고 있습니다. 시간이 다소 걸릴 수 있습니다.",
      duration: 3000,
    });
  };

  const getStatusBadge = (status: string, color: string) => {
    const colorClasses = {
      green: "bg-green-100 text-green-800",
      blue: "bg-blue-100 text-blue-800", 
      gray: "bg-gray-100 text-gray-800",
      orange: "bg-orange-100 text-orange-800"
    };
    
    return (
      <span className={`inline-block px-3 py-1 text-sm rounded-full ${colorClasses[color as keyof typeof colorClasses]}`}>
        {status}
      </span>
    );
  };

  return (
    <div className="min-h-screen bg-warm-brown-50">
      <Header />
      
      <main className="pt-24 pb-16">
        <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between items-center mb-8">
            <div>
              <h1 className="text-3xl md:text-4xl font-light text-gray-900 mb-4">
                나의 글 관리
              </h1>
              <p className="text-lg text-gray-600">
                작성한 글을 관리하고 출간 요청을 할 수 있습니다
              </p>
            </div>
            
            <Link to="/author-submission">
              <Button className="bg-warm-brown-700 hover:bg-warm-brown-800 text-white flex items-center gap-2">
                <Plus className="w-4 h-4" />
                새 글 작성하기
              </Button>
            </Link>
          </div>
          
          <div className="space-y-6">
            {books.map((book) => (
              <div key={book.id} className="bg-white rounded-lg border border-warm-brown-200 p-6">
                <div className="flex flex-col lg:flex-row lg:items-center lg:justify-between gap-4">
                  <div className="flex-1">
                    <div className="flex items-center gap-3 mb-2">
                      <h3 className="text-xl font-medium text-gray-900">{book.title}</h3>
                      {getStatusBadge(book.status, book.statusColor)}
                    </div>
                    
                    <div className="flex flex-wrap gap-4 text-sm text-gray-600">
                      <span>최종 수정: {book.lastUpdate}</span>
                      <span>단어 수: {book.wordCount}</span>
                      {book.views && <span>조회 수: {book.views}</span>}
                    </div>
                  </div>
                  
                  <div className="flex gap-3">
                    {book.status === "출간 완료" && (
                      <Button variant="outline" size="sm" className="border-warm-brown-300">
                        편집 불가
                      </Button>
                    )}
                    
                    {book.status === "최종저장" && (
                      <>
                        <Link to="/writing-editor">
                          <Button variant="outline" size="sm" className="flex items-center gap-2 border-warm-brown-300 text-warm-brown-700">
                            <Edit className="w-4 h-4" />
                            편집
                          </Button>
                        </Link>
                        <Button 
                          size="sm" 
                          className="bg-warm-brown-700 hover:bg-warm-brown-800 text-white flex items-center gap-2"
                          onClick={handlePublishRequest}
                        >
                          <Send className="w-4 h-4" />
                          출간 요청
                        </Button>
                      </>
                    )}
                    
                    {book.status === "임시저장" && (
                      <Link to="/writing-editor">
                        <Button variant="outline" size="sm" className="flex items-center gap-2 border-warm-brown-300 text-warm-brown-700">
                          <Edit className="w-4 h-4" />
                          편집
                        </Button>
                      </Link>
                    )}
                    
                    {book.status === "출간 요청됨" && (
                      <Button variant="outline" size="sm" className="border-warm-brown-300">
                        편집 불가
                      </Button>
                    )}
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </main>
      
      <Footer />
    </div>
  );
};

export default MyBooks;
